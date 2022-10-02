import React from 'react'
import * as Location from 'expo-location'

export const GlobalContext = React.createContext();

export const GlobalStorage = ({children}) => {

    const [coords, setCoords] = React.useState(null)
    const [name, setName] = React.useState("")

    React.useEffect(() => {
        saveLocation()
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
    <GlobalContext.Provider value={{coords, setCoords, name, setName}}>
        {children}
    </GlobalContext.Provider>
    )
}