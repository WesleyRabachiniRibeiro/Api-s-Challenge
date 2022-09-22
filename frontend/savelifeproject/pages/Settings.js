import React, { useEffect, useState } from 'react';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, TouchableOpacity, TextInput, FlatList } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import PhoneItem from '../components/PhoneItem';

export default function Settings() {


  const [ddd, setDdd] = useState('')
  const [phoneNumber, setPhoneNumber] = useState('')
  const [numbers, setNumbers] = useState([])
  const [itemNumbers, setItemNumbers] = useState([])

  useEffect(() => {
    setNumberList()
  }, [])

  async function saveData(){
    const novoId = await geraId()
    const telefone = {
      id: novoId.toString(),
      ddd: ddd,
      number: phoneNumber
    }
    try{
      const jsonValue = JSON.stringify(telefone)
      itemNumbers.forEach(async (item) => {
        if(item.number === telefone.number){
          var index = itemNumbers.indexOf(item)
          console.log(index)
          var removedItem = itemNumbers.splice(index, 1)
          console.log(removedItem)
          await AsyncStorage.removeItem(removedItem[0].id)
          await AsyncStorage.setItem(telefone.id, jsonValue)
        }
      })
      await AsyncStorage.setItem(telefone.id, jsonValue)
      setNumberList()
    }catch (e){
      console.log(e)
    }
  }

  async function geraId(){
    const todasChaves = await AsyncStorage.getAllKeys()
    if(todasChaves <= 0){
      return 1
    }
    return todasChaves.length + 1
  }

  async function getData(key){
    try{
      const value = await AsyncStorage.getItem(key)
      if(value !== null){
        console.log(value)
      }
    } catch (e){
      console.log(e)
    }
  }

  async function setNumberList(){
    const list = []
    const allKeys = await AsyncStorage.getAllKeys()
    const item = await AsyncStorage.multiGet(allKeys)
    item.forEach((item) => {
      const obj = JSON.parse(item[1])
      list.push(obj)
    })
    setItemNumbers(list)
    console.log(`All Keys ${allKeys}`)
  }

  async function editPhone(item){
    setDdd(item.ddd)
    setPhoneNumber(item.number)

  }

  async function removePhone(item){
    var index = itemNumbers.indexOf(item)
    var removedItem = itemNumbers.splice(index, 1)
    await AsyncStorage.removeItem(removedItem[0].id)
  }

  function getAllData(){
    return itemNumbers.map((item) => (
      <PhoneItem key={item.id} ddd={item.ddd} number={item.number} onEditar={() => editPhone(item)} onDeletar={() => {
        removePhone(item)
        setNumberList()
      }}/>
    ))
  }
    return (
      <View style={styles.container}>
        <Text style={styles.title}>Lista de Contato</Text>
        <Text style={styles.title}>Digite um novo telefone aqui</Text>
        <View style={{flexDirection: 'row', marginLeft: 20}}>
          <TextInput placeholder="DDD *" style={styles.input} onChangeText={setDdd} keyboardType='numeric' value={ddd}/>
          <TextInput placeholder="Numero De Telefone *" style={styles.input} onChangeText={setPhoneNumber} keyboardType='phone-pad' value={phoneNumber}/>
          <TouchableOpacity onPress={saveData}>
            <Text style={{marginTop: 45, marginLeft: 20, color: 'green'}}>Confirmar</Text>
          </TouchableOpacity>
        </View>
        {getAllData()}
        <StatusBar style="auto" />
      </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingTop: 25,
        backgroundColor: '#FCFCFC',
      },
      title: {
        color: '#000',
        paddingHorizontal: 25,
        fontSize: 23,
        lineHeight: 40,
        marginTop: 10
      },
      input: {
        backgroundColor: '#fff',
        marginVertical: 5,
        paddingVertical: 10,
        paddingHorizontal: 10,
        marginTop: 30,
        borderColor: 'black',
        borderWidth: 1
      },
})