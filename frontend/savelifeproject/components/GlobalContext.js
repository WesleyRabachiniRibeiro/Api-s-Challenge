import React from 'react'
import * as Location from 'expo-location'
import {initializeApp, getApps} from "firebase/app";
import {getDatabase, set, ref} from "firebase/database"

export const GlobalContext = React.createContext();

export const GlobalStorage = ({children}) => {

    const [coords, setCoords] = React.useState(null)
    const [id, setId] = React.useState(null)
    const [name, setName] = React.useState("")
    const [email, setEmail] = React.useState("")
    const [phone, setPhone] = React.useState("")
    const [picture, setPicture] = React.useState([])
    const [age, setAge] = React.useState(null)
    const [cpf, setCpf] = React.useState("")
    const [susCard, setSusCard] = React.useState("")
    const [healthPlan, setHealthPlan] = React.useState("")
    const [roles, setRoles] = React.useState("")
    const [token, setToken] = React.useState("")
    const [isRequest, setIsRequest] = useState(false);


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

    function writeUserCoords(){
      set(ref(database, 'users/' + "paciente"), {
        userId: 1,
        coords: {
          latitude: coords.latitude,
          longitude: coords.longitude
        }
      })
    }
  
    function writeAmbulanceCoords(){
      set(ref(database, 'users/' + "ambulancia"), {
        coords: {
          latitude: coords.latitude,
          longitude: coords.longitude
        }
      })
    }

    React.useEffect(() => {
        saveLocation()
        writeUserCoords()
    }, []) 

    const saveLocation = async () => {
      const {status} = await Location.requestForegroundPermissionsAsync()
        if(status === 'granted'){
        let location = await Location.getCurrentPositionAsync({enableHighAccuracy: true})
        setCoords({
          latitude: location.coords.latitude,
          longitude: location.coords.longitude,
          latitudeDelta: 0.05,
          longitudeDelta: 0.05
        })
      }else{
        console.log('Access Denied')
      }}

    return (
    <GlobalContext.Provider value={{id, setId, coords, setCoords, name, setName, email, setEmail, phone, setPhone, age, setAge, cpf, setCpf, susCard, setSusCard, picture, setPicture, healthPlan, setHealthPlan, roles, setRoles, token, setToken, writeUserCoords, writeAmbulanceCoords, isRequest, setIsRequest}}>
        {children}
    </GlobalContext.Provider>
    )
}