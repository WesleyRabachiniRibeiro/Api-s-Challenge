import React, { useEffect, useState } from "react";
import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View, TouchableOpacity, TextInput, Image, FlatList, } from "react-native";
import axios from "axios";
import base64 from "react-native-base64";
import * as FileSystem from "expo-file-system";
import { Audio } from "expo-av";
import * as Speech from 'expo-speech';
import { Ionicons } from "@expo/vector-icons";
import Accordion from "../components/Accordion";
import { GlobalContext } from '../components/GlobalContext';


const { CHATBOT_KEY } = process.env;
const key = CHATBOT_KEY;
const encodedKey = base64.encode(`apikey:${key}`);

export default function Chatbot() {
  const [chatMessage, setChatMessage] = useState([]);
  let [context, setContext] = useState("");
  const [lista, setLista] = useState([]);
  const [counter, setCounter] = useState(1);
  const [message, setMessage] = useState("")
  const [requestId, setRequestId] = useState(false);
  const [permission, setPermission] = React.useState("");
  const [recording, setRecording] = React.useState("");

  const global = React.useContext(GlobalContext)

  function Message(props) { 

    const bgColor = props.user === "bot" ? "#6914FF" : "#FFFFFF"
    const color = props.user === "bot" ? "#FFFFFF" : "#000000"
    const alignSelf = props.user === "bot" ? "flex-start" : "flex-end"
    const borderBot = props.user === "bot" ? 0 : 10
    const borderUser = props.user === "user" ? 0 : 10


    return (
      <View style={[styles.bloom, {backgroundColor: bgColor, alignSelf: alignSelf, borderBottomLeftRadius: borderBot, borderBottomRightRadius: borderUser}]}>
          <Text style={[styles.message, {color: color}]}>{props.message}</Text>
      </View>
    );
  }

  console.log(global.id)

  const callAmbulance = () => {
    axios.post("https://api-challenge.azurewebsites.net/v1/request/",
    {
      user: global.id,
      hospital : "R. Conselheiro Brotero 1486",
      urgent: "ALTA",
      description : message
    },
    {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Basic ${global.token}`
      },
    }).then((res) => {
      console.log(res.data)
      setRequestId(res.data.id)
    }).catch((err) => {
      console.log(err)
    })

    if(global.roles.indexOf("ROLE_AMBULANCE")){
      global.writeAmbulanceCoords()
    }

    if(global.roles.indexOf("ROLE_USER")){
      global.writeUserCoords()
    }
  }

  const updateCall = () => {
    axios.put(`https://api-challenge.azurewebsites.net/v1/request/${requestId}`,
    {
      description : message
    },
    {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Basic ${global.token}`
      },
    }).then((res) => {
      console.log(res.data)
    }).catch((err) => {
      console.log(err)
    })
  }


  const saveList = async (user, bot) =>{
    if(user === ""){
      console.log("message: " + bot)
      if(bot.length > 1){
          setLista([...lista, {id: counter, message: [...bot].join("\n\n"), user: "bot"} ]);
      }else{
        setLista([...lista, {id: counter, message: bot, user: "bot"} ]);
      }
    } else{
      setLista([...lista, {id: counter, message: user, user: "user"} ])
    }
    setCounter(counter + 1);
    setChatMessage("")
    setMessage("")
  }

  useEffect(() => {
    if(chatMessage.length > 1){
      chatMessage.map(value =>{
        saveList("", chatMessage)
        Speech.speak(value, {language: "pt-BR"});
      })
    }else{        
      if(chatMessage[0] != null){
        Speech.speak(chatMessage[0], {language: "pt-BR"});
        saveList("", chatMessage)
      }
    }
  },[chatMessage])


  useEffect(() => {
    axios
      .post(
        `https://api.us-south.assistant.watson.cloud.ibm.com/instances/41d486a5-09a3-4609-9629-4de348993850/v1/workspaces/5a21c745-ec95-4e46-a1e2-41f23737b1e0/message?version=2018-09-20`,
        {
          input: { text: message},
        },
        {
          headers: {
            Authorization: `Basic ${encodedKey}`,
            "Content-Type": "application/json",
          },
        }
      )
      .then((res) => {
        setContext(res.data.context)
        setChatMessage(res.data.output.text)
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);

  const sendMessage = (messageUser) => {
    axios
      .post(
        `https://api.us-south.assistant.watson.cloud.ibm.com/instances/41d486a5-09a3-4609-9629-4de348993850/v1/workspaces/5a21c745-ec95-4e46-a1e2-41f23737b1e0/message?version=2018-09-20`,
        {
          input: { text: messageUser},
          context,
        },
        {
          headers: {
            Authorization: `Basic ${encodedKey}`,
            "Content-Type": "application/json",
          },
        }
      )
      .then((res) => {
        setContext(res.data.context)
        setChatMessage(res.data.output.text)
        if(res.data.output.text == "Respire fundo e conte para mim o que aconteceu, enquanto isso iremos fazer a requisição de sua ambulância"){
          // callAmbulance()
          global.setIsRequest(true)
        }
        if(global.isRequest){
          updateCall()
        }
      })
      .catch((err) => {
        console.error(err);
      });

  }

  const userMessage = async (recording) => {
    const formData = new FormData();
    formData.append("file", {
      uri: recording.getURI(),
      name: "test.wav",
      type: "audio/wav",
    });
    await axios
      .post('https://nodered-app-1tdsr-fiap-2021.mybluemix.net/transcription', formData, {
        responseType: 'text',
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then(response => {
        saveList(response.data, "")
        sendMessage(response.data)
      })
      .catch(error => {
        console.log(error);
        console.error(error.response);
      });
  }

  async function startRecording() {
    try {
      const permission = await Audio.requestPermissionsAsync();
      if (permission.status === "granted") {
        await Audio.setAudioModeAsync({
          allowsRecordingIOS: true,
          playsInSilentModeIOS: true,
        });
        const recording = new Audio.Recording();
        try {
          await recording.prepareToRecordAsync(Audio.RECORDING_OPTIONS_PRESET_HIGH_QUALITY);
          await recording.startAsync();
        } catch (err) {
          console.log(err)
        }
        setRecording(recording)
      } else {
        setPermission("Please grant permission to app to access microphone");
      }
    } catch (err) {
      console.error("Failed to start recording", err);
    }
  }
  
  async function stopRecording() {
    await recording.stopAndUnloadAsync();
    let resp = recording
    setRecording("")
    userMessage(resp)
  }

  return (
    <View style={styles.container}>
      <StatusBar style="light" backgroundColor="#000000" />
      <View style={styles.callContainer}>
        <Image source={require("../assets/phone.png")} style={styles.call}/>
      </View>
      <Text style={styles.title}>Chamada em andamento</Text>
      <FlatList  data={lista} keyExtractor={item => item.id} renderItem={({item})=> {
          return <Message {...item}/>
        }} style={styles.flatList}/>
      {/* <View style={styles.inputContainer}>
        <TextInput placeholder="Digite aqui..." style={styles.input} onChangeText={setMessage} value={message}/>
        <TouchableOpacity style={styles.button} onPress={() => {
          sendMessage()
          saveList()
          setMessage("")
          }}>
          <Ionicons name="send" style={styles.buttonText}/>
        </TouchableOpacity>
      </View> */}
      <View style={styles.inputContainer}>
        <Text>{permission}</Text>
        <TouchableOpacity style={styles.mic}
          onPress={recording ? stopRecording : startRecording}>
            {recording ? <Ionicons name="mic" size={50} style={styles.mic}/> : <Ionicons name="mic-outline" size={50} style={styles.mic}/>}
        </TouchableOpacity>
      </View>
      {global.isRequest && <Accordion/>}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 10,
    backgroundColor: "#FFFFFF",
    paddingHorizontal: 20
  },
  callContainer: {
    backgroundColor: "#FA533E",
    alignSelf: 'center',
    padding: 20,
    borderRadius: 90
  },
  call: {
    width: 50,
    height: 50,
    resizeMode: "contain"
  },
  title: {
    color: "#000",
    fontSize: 23,
    alignSelf: "center",
    marginTop: 10,
  },
  inputContainer: {
    flexDirection: "row",
    alignSelf: "center",
    marginTop: 10,
    marginBottom: 70
  },
  input: {
    backgroundColor: '#e0e0e0',
    paddingVertical: 10,
    paddingHorizontal: 10,
    borderTopLeftRadius: 15,
    borderBottomLeftRadius: 15,
    flex: 2
  },
  buttonText: {
    fontSize: 30,
    alignSelf: "center",
    backgroundColor: "#6914FF",
    paddingVertical: 7,
    paddingHorizontal: 5,
    borderTopRightRadius: 15,
    borderBottomRightRadius: 15,
    color: "#ffffff"
  },
  flatList:{
    marginHorizontal: 10,
    marginTop: 20,
    marginBottom: 0,
    flex: 1,
  },
  bloom: {
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
    elevation: 5,
    padding: 10,
    marginBottom: 10,
    borderTopStartRadius: 10,
    borderTopEndRadius: 10
  },
  message: {
    fontSize: 20,
  },
  messageBox: {
    backgroundColor: "red"
  },
  mic: {
    color: "red"
  }
});
