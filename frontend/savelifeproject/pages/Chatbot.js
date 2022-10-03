import React, { useEffect, useState } from "react";
import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View, TouchableOpacity, TextInput, Image, FlatList, Button } from "react-native";
import axios from "axios";
import base64 from "react-native-base64";
import * as Speech from 'expo-speech';
import { Ionicons } from "@expo/vector-icons";
import Accordion from "../components/Accordion";
import Voice  from "@react-native-voice/voice";

const { CHATBOT_KEY } = process.env;
const key = CHATBOT_KEY;
const encodedKey = base64.encode(`apikey:${key}`);

export default function Chatbot() {
  const [message, setMessage] = useState("");
  const [chatMessage, setChatMessage] = useState([]);
  let [context, setContext] = useState("");
  const [lista, setLista] = useState([]);
  const [counter, setCounter] = useState(1);








  let [started, setStarted] = useState(false);
  let [results, setResults] = useState([]);

  useEffect(() => {
    Voice.onSpeechResults = onSpeechResults;
    Voice.onSpeechError = onSpeechError;
    return () => {
      Voice.destroy().then(Voice.removeAllListeners);
    }
  }, []);

  const startSpeechToText = async () => {
    await Voice.start("pt-Br");
    setStarted(true);
  };

  const stopSpeechToText = async () => {
    await Voice.stop();
    setStarted(false);
  };

  const onSpeechResults = (result) => {
    setResults(result.value);
  };

  const onSpeechError = (error) => {
    console.log(error);
  };









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

  const saveList = async (chatMessage) =>{
    if(message === ""){
      if(chatMessage.length > 1){
          setLista([...lista, {id: counter, message: [...chatMessage].join("\n\n"), user: "bot"} ]);
      }else{
        setLista([...lista, {id: counter, message: chatMessage, user: "bot"} ]);
      }
    } else{
      setLista([...lista, {id: counter, message: message, user: "user"} ])
    }
    setCounter(counter + 1);
    setChatMessage("")
    setMessage("")
  }

  useEffect(() => {
    if(chatMessage.length > 1){
      chatMessage.map(value =>{
        saveList(chatMessage)
        Speech.speak(value, {language: "pt-BR"});
      })
    }else{
      if(chatMessage[0] != null){
        Speech.speak(chatMessage[0], {language: "pt-BR"});
        saveList(chatMessage)
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

  const sendMessage = () => {
    axios
      .post(
        `https://api.us-south.assistant.watson.cloud.ibm.com/instances/41d486a5-09a3-4609-9629-4de348993850/v1/workspaces/5a21c745-ec95-4e46-a1e2-41f23737b1e0/message?version=2018-09-20`,
        {
          input: { text: message},
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
      })
      .catch((err) => {
        console.error(err);
      });

  }

  return (
    <View style={styles.container}>
      <StatusBar style="light" backgroundColor="#000000" />
      <View style={styles.callContainer}>
        <Image source={require("../assets/phone.png")} style={styles.call}/>
      </View>
      <Text style={styles.title}>Chamada em andamento</Text>
      <FlatList data={lista} keyExtractor={item => item.id} renderItem={
        ({item})=> {
          return <Message {...item}/>
        }} style={styles.flatList}/>
      <View style={styles.inputContainer}>
        <TextInput placeholder="Digite aqui..." style={styles.input} onChangeText={setMessage} value={message}/>
        <TouchableOpacity style={styles.button} onPress={() => {
          sendMessage()
          saveList()
          setMessage("")
          }}>
          <Ionicons name="send" style={styles.buttonText}/>
        </TouchableOpacity>
      </View>
      {!started ? <Button title='Start Speech to Text' onPress={startSpeechToText} /> : undefined}
      {started ? <Button title='Stop Speech to Text' onPress={stopSpeechToText} /> : undefined}
      {/* <Accordion/> */}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 40,
    backgroundColor: "#FFFFFF",
    paddingHorizontal: 20
  },
  callContainer: {
    backgroundColor: "#FA533E",
    alignSelf: 'center',
    padding: 40,
    borderRadius: 90
  },
  call: {
    width: 100,
    height: 100,
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
    alignItems: "center",
    marginTop: 10,
    marginBottom: 100
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
    flex: 1
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
  }
});
