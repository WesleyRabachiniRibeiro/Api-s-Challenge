import React, { useEffect, useState, useRef } from 'react'
import {StyleSheet, SafeAreaView, StatusBar, View } from 'react-native'
import MapView from 'react-native-maps'
import * as Location from 'expo-location'
import axios from 'axios';


export default function Mapa(props){

    const [origin, setOrigin] = useState(null)
    const [destination, setDestination] = useState(null)
    const [hospital, setHospital] = useState([])
    const [url, setUrl] = useState("")

    useEffect( () => {
        (async () => {
            const {status} = await Location.requestForegroundPermissionsAsync()
            if(status === 'granted'){
                let location = await Location.getCurrentPositionAsync({enableHighAccuracy: true})
                setOrigin({
                    latitude: location.coords.latitude,
                    longitude: location.coords.longitude,
                    latitudeDelta: 0.05,
                    longitudeDelta: 0.05
                })
                
                setUrl(`https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=` +
                location.coords.latitude +
                `,` +
                location.coords.longitude +
                `&radius=` +
                1*1000 +
                `&type=` +
                `hospital` +
                `&key=` +
                `AIzaSyDR5JzvwwHZDgDqsVmawcm3Zs2sHVwVH_g`)
                console.log(url)
            }else {
                throw new Error('Location permission not granted')
            }
        })();
    }, [])

    useEffect(()=>{
    if(url !== ''){
        axios.get(
            url,
        {
            headers: {
            "Content-Type": "application/json",
        },
        })
        .then((res) => {
            setHospital(res.data.results)
        })
        .catch((err) => {
            console.error(err);
        });
    }
    },[url]);

    console.log(hospital)

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
    },
    search: {
        height: "40%",
    }
})