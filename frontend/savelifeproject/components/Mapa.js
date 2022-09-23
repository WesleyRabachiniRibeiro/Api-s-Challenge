import React, { useEffect, useState } from 'react'
import {Text, StyleSheet, SafeAreaView, StatusBar } from 'react-native'
import MapView from 'react-native-maps'
import * as Location from 'expo-location'

export default function Mapa(props){

    const [origin, setOrigin] = useState(null)
    const [destination, setDestination] = useState(null)

    useEffect( () => {
        (async function (){
            const {status} = await Location.requestForegroundPermissionsAsync()
            if(status === 'granted'){
                let location = await Location.getCurrentPositionAsync({enableHighAccuracy: true})
                setOrigin({
                    latitude: location.coords.latitude,
                    longitude: location.coords.longitude,
                    latitudeDelta: 0.05,
                    longitudeDelta: 0.05
                })
            } else {
                throw new Error('Location permission not granted')
            }
        })();
    }, [])

    return(
        <SafeAreaView style={styles.viewPrincipal}>
            <StatusBar style="light" backgroundColor={"#000000"}/>
            <MapView
            initialRegion={origin}
            showsUserLocation={true}
            style={{flex: props.flex ? props.flex : 1}}
            />
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    viewPrincipal:{
        flex: 1,
        marginTop: StatusBar.currentHeight
    }
})