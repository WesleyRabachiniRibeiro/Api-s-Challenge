import React, { useEffect, useState } from 'react'
import { View, Text, TextInput, Button } from 'react-native'
import { FlatList } from 'react-native-gesture-handler';
import { GlobalContext } from '../components/GlobalContext';
import uuid from 'react-native-uuid';
import {initializeApp, getApps} from "firebase/app";
import {getDatabase, set, ref, onValue, remove} from "firebase/database"
import axios from 'axios';

export default function AccountSettings(props){
    const global = React.useContext(GlobalContext)

    const [telefone, setTelefone] = useState(global.phone)

    const firebaseConfig = {
      apiKey: "AIzaSyCFPeKepVl4QVqGXJLwdAbmADOQcMrW_-0",
      authDomain: "savelife-74f45.firebaseapp.com",
      databaseURL: "https://savelife-74f45-default-rtdb.firebaseio.com",
      projectId: "savelife-74f45",
      storageBucket: "savelife-74f45.appspot.com",
      messagingSenderId: "1086577315428",
      appId: "1:1086577315428:web:910a240e7ec688b0721971"
    };

    let app;
    // Initialize Firebase
    if(getApps.length === 0){
        app = initializeApp(firebaseConfig);
    }

    const database = getDatabase(app)

    function modificaTelefone(){
      axios.post(`https://api-challenge.azurewebsites.net/v1/user/${global.id}`,{
        "phone": telefone,
      }, 
      {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Basic ${global.token}`
        },
      }).then((res) => {
        console.log('Modificou')
        console.log(res.data)
      }).catch((err) => {
        console.log(global.id)
        console.log(err)
      })
    }

    function excluiConta(){
      axios.delete(`https://api-challenge.azurewebsites.net/v1/user/${global.id}`, {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Basic ${global.token}`
        },
      }).then((res) => {
        console.log(res.data)
      }).catch((err) => {
        console.log(global.id)
        console.log(err)
      })
    }
    
    return (
        <View style={{marginTop: 40}}>
            <Text>Seu telefone: {global.phone} </Text>
            <Text style={{marginTop: 50}}>Digite aqui para mudar seu telefone</Text>
            <TextInput onChangeText={setTelefone} keyboardType="number-pad" style={{borderWidth: 1, padding: 5}}/>
            <View >
              <Button title={"Mudar Telefone"} onPress={() => {modificaTelefone()}}/>
              <Button title="Deletar Conta" color={'red'} onPress={() => {excluiConta()}}/>
            </View>
        </View>
    )
}