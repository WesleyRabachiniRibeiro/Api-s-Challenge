import React, { useState } from 'react';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, TextInput, View, TouchableOpacity, Button } from 'react-native';
import CheckBox from 'expo-checkbox';
import { ScrollView } from 'react-native-gesture-handler';
import axios from "axios";
import MaskInput from 'react-native-mask-input';

export default function Cadastro (props) {

    const [boxIsChecked, setBoxIsChecked] = useState(false);
    const [name, setName] = useState("");
    const [cpf, setCpf] = useState("");
    const [date, setDate] = useState(null);
    const [age, setAge] = useState("");
    const [cellphoneNumber, setCellPhoneNumber] = useState("");
    const [susCard, setSusCard] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    function reset() {
      setBoxIsChecked(false)
      setName("");
      setCpf("");
      setDate(null);
      setAge("");
      setCellPhoneNumber("");
      setSusCard("");
      setEmail("");
      setPassword("");
      setConfirmPassword("");
    }


    function print(){
      console.log(name)
      console.log(cpf)
      console.log(date)
      console.log(cellphoneNumber)
      console.log(email)
      console.log(susCard)
      console.log(password)
      console.log(confirmPassword)
    }

    function getAge() {
      if(date != null){

        let day = date.substring(0, 2)
        let month = date.substring(2, 4)
        let year = date.substring(4, 9) 
        
        let today = new Date()
        let year_now = today.getFullYear()
        let month_now = today.getMonth() + 1
        let day_now = today.getDate()
        setAge(year_now - year);
        
        if (month_now < month|| month_now == month && day_now < day) {
          setAge(age);
        }
      }
    }
  


  const save = () => {
      print()
      axios.post(
        'https://api-challenge.azurewebsites.net/v1/user/',
        {
          "name" : name,
          "age" : parseInt(age),
          "phone" : cellphoneNumber,
          "email" : email,
          "password" : password,
          "susCard" : susCard,
          "cpf" : cpf,
          "roles" : [
              {
              "name" : "ROLE_USER"
              }
          ]
        },
        {
          headers: {
            'Content-Type': 'application/json',
          }
        })
        .then((res) => {
          console.log(res.data)
          reset()
          props.navigation.navigate("Login")
        })
        .catch((err) => {
          console.error(err);
        })
      console.log("pão")
    }

    return (
      <ScrollView>
        <View style={styles.container}>
          <Text style={styles.title}>Cadastro</Text>
          <View style={{flexDirection: 'row', alignItems: 'baseline', marginLeft: 15}}>
            <Text style={styles.subtitle}>Já tem uma conta?</Text>
            <TouchableOpacity onPress={() => props.navigation.navigate("Login")}>
              <Text style={[styles.link, styles.a]}>Clique aqui para entrar</Text>
            </TouchableOpacity>
          </View>

          <View>
              <TextInput placeholder="Nome Completo *" style={styles.input} onChangeText={setName} keyboardType='ascii-capable'/>
              <MaskInput placeholder="CPF *" style={styles.input} onChangeText={(masked, unmasked) => {setCpf(unmasked)}} value={cpf} keyboardType='numeric' mask={[/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-',/\d/, /\d/]}/>
              <MaskInput placeholder="Data de nascimento (DD/MM/AAAA) *" style={styles.input} onChangeText={(masked, unmasked) => {setDate(unmasked)}} value={date} keyboardType='number-pad' mask={[/\d/, /\d/, '/',  /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/]}/>
              <MaskInput placeholder="Telefone Celular*" style={styles.input} onChangeText={(masked, unmasked) => {setCellPhoneNumber(unmasked)}} value={cellphoneNumber} keyboardType='phone-pad' mask={['(', /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/]}/>
              <TextInput placeholder="E-mail *" style={styles.input} onChangeText={setEmail}  keyboardType='email-address'/>
              <MaskInput placeholder="Cartão Sus *" style={styles.input} onChangeText={(masked, unmasked) => {setSusCard(unmasked)}} value={susCard}  keyboardType='numeric' mask={[/\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/,  ' ', /\d/, /\d/, /\d/, /\d/]}/>
              <TextInput placeholder="Senha *"  style={styles.input} onChangeText={setPassword} secureTextEntry={true}/>
              <TextInput placeholder="Confirmar senha *" style={styles.input} onChangeText={setConfirmPassword} secureTextEntry={true}/>
              <View style={styles.checkboxContainer}>
                  <Text style={styles.textCheckbox}>Aceito os </Text>
                  <TouchableOpacity style={{flexDirection: 'row', alignItems: 'center'}} onPress={() => setBoxIsChecked(!boxIsChecked)}>
                    <Text style={styles.a}>termos de condições</Text>
                    <CheckBox
                    style={styles.checkbox}
                    value={boxIsChecked}
                    onValueChange={setBoxIsChecked}
                    color={boxIsChecked ? '#6A5ACD' : undefined}
                  />
                  </TouchableOpacity>
                  
              </View>
              <TouchableOpacity style={[styles.button, {backgroundColor: boxIsChecked ? '#6914FF' : '#A9A9A9'}]} disabled={!boxIsChecked} onPress={() => {
                getAge()
                save()              
              }}>
                  <Text style={styles.buttonText}>Cadastrar</Text>
              </TouchableOpacity>
          </View>
          <StatusBar style="auto" />
        </View>
      </ScrollView>
    );
}

const styles = StyleSheet.create({
  container: {
    paddingTop: 50,
    paddingHorizontal: 25,
    backgroundColor: '#FCFCFC',
    flexGrow: 1,
  },
  title: {
    fontSize: 23,
    color: '#000',
    marginBottom: 10,
    textAlign: 'center'
  },
  subtitle: {
    fontSize: 16,
    color: '#000',
    marginBottom: 10,
    textAlign: 'center',
  },
  a: {
    color: '#6A5ACD'
  },

  input: {
      backgroundColor: '#fff',
      marginVertical: 5,
      paddingVertical: 10,
      paddingHorizontal: 10,
      borderRadius: 15,

      shadowColor: "#000",
      shadowOffset: {
	    width: 0,
	    height: 5,
      },
      shadowOpacity: 0.04,
      shadowRadius: 10,
      elevation: 50
  },
  checkboxContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 10,
    justifyContent: 'center'
  },
  checkbox: {
    marginLeft: 12,
    marginRight: 10,
    borderColor: '#FCFCFC',
    width: 25,
    height: 25,
    backgroundColor: '#fff',
    shadowColor: "#000",
      shadowOffset: {
	    width: 0,
	    height: 5,
      },
      shadowOpacity: 0.04,
      shadowRadius: 10,
      elevation: 50
  },
  textCheckbox: {

  },
  button: {
      backgroundColor: '#6914FF',
      padding: 10,
      borderRadius: 15,
      marginTop: 15,
  },
  buttonText: {
      textAlign: 'center',
      color: '#fff'
  },
  link:{
    marginLeft: 5
  }
});
