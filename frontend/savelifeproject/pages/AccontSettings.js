import axios from 'axios';
import React, { useState } from 'react'
import { View, Text, TextInput, Button } from 'react-native'
import { FlatList } from 'react-native-gesture-handler';
import { GlobalContext } from '../components/GlobalContext';

export default function AccountSettings(props){

    const [telefone, setTelefone] = useState("")

    const global = React.useContext(GlobalContext)
    
    const add = () => {
        axios.get(
            `https://savelife-74f45-default-rtdb.firebaseio.com/telefone`,
            
            )
            .then((res) => {
              console.log(res.data)
              console.log(res.key)
              console.log(res.data.key)
            })
            .catch((err) => {
              console.error(err);
            })
    }

    const update = () => {
        axios.put(
            `https://savelife-74f45-default-rtdb.firebaseio.com/`,
            {
              "number" : telefone,
            },
            {
              headers: {
                'Content-Type': 'application/json',
              }
            })
            .then((res) => {
              console.log(res.data)
            })
            .catch((err) => {
              console.error(err);
            })
    }

    return (
        <View style={{marginTop: 40}}>
            <Text>Adicionar Telefone na Conta:</Text>
            <TextInput onChangeText={setTelefone} keyboardType="number-pad" style={{borderWidth: 1, padding: 5}}/>
            <Button title='Adicionar Telefone' onPress={() => {update()}}/>
            <FlatList />
        </View>
    )
}