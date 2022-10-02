import React, { useEffect, useState } from 'react';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, TextInput, View, TouchableOpacity, PermissionsAndroid } from 'react-native';
import CheckBox from 'expo-checkbox';
import { GlobalContext } from '../components/GlobalContext';

export default function Login(props) {

  const global = React.useContext(GlobalContext)

  const [boxIsChecked, setBoxIsChecked] = useState(false);
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");

    const login = () => {
      if(email === "A"){
        props.navigation.navigate("HomeNavigation")
      }else{
        global.setName("Carlos Alberto")
        props.navigation.navigate("AmbulanceHome")
      }
    }
    return (
      <View style={styles.container}>
        <Text style={styles.title}>Login</Text>
        <View style={{alignItems: 'center', paddingBottom: 10}}>
          <Text style={styles.subtitle}>Ainda não tem uma conta?</Text>
          <TouchableOpacity onPress={() => {props.navigation.navigate("Cadastro")}}>
            <Text style={styles.a}>Clique aqui para cadastrar.</Text>
          </TouchableOpacity>
        </View>
        <View>
            <TextInput placeholder="E-mail *" style={styles.input} onChangeText={setEmail}  keyboardType='email-address'/>
            <TextInput placeholder="Senha *" style={styles.input} onChangeText={setPassword} secureTextEntry={true}/>
            <View style={styles.checkboxContainer}>
                <CheckBox 
                value={boxIsChecked}
                onValueChange={setBoxIsChecked}
                style={styles.checkbox}
                />
                <Text style={styles.textCheckbox}>Manter minha sessão</Text>
            </View>
            <TouchableOpacity style={styles.button} onPress={() => {login()}}>
                <Text style={styles.buttonText}>Entrar</Text>
            </TouchableOpacity>
        </View>
        <StatusBar style="auto" />
      </View>
    );
}

const styles = StyleSheet.create({
  container: {
    paddingTop: 50,
    paddingHorizontal: 25,
    backgroundColor: '#FCFCFC',
    flex: 1
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
    marginTop: 10
  },
  checkbox: {
    marginRight: 10,
    marginLeft: 5,
    borderColor: '#FCFCFC',
    width: 20,
    height: 20,
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
  }
});
