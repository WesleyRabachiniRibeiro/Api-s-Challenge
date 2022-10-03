import React from 'react'
import {Text, SafeAreaView} from 'react-native'
import { StatusBar } from 'expo-status-bar';
import { GlobalContext } from '../components/GlobalContext';

export default function AmbulanceHome(){
    const global = React.useContext(GlobalContext)
    const [hasOcorrencia, setHasOcorrencia] = React.useState(true)
    let name = global.name
    
    return(
        <SafeAreaView>
            <Text style={{fontSize: 20, marginTop: 40}}>Bem vindo(a) {name}</Text>
            {hasOcorrencia &&
            <><Text style={{fontSize: 20, marginTop: 40}}>Por enquanto não temos nenhuma ocorrencia para te mostrar, porém fique de olho!</Text>
            <Text style={{fontSize: 20, marginTop: 0}}>A qualquer hora um acidente pode acontecer</Text></>}
            <StatusBar style="auto" />
        </SafeAreaView>
    )
}