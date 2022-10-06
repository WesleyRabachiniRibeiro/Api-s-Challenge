import React, { useEffect, useState, useRef } from 'react';
import {StyleSheet, SafeAreaView, StatusBar } from 'react-native';
import MapView from 'react-native-maps';
import MapViewDirections from 'react-native-maps-directions';
import { GlobalContext } from '../components/GlobalContext';

const {MAPS_KEY}  = process.env;

export default function MapaAmbulancia(props){
    const global = React.useContext(GlobalContext)
    let location = global.coords

    const mapEl=useRef(null);

    const [origin, setOrigin] = useState(null)
    const [destination, setDestination] = useState(null)

    useEffect( () => {
        setOrigin({
            latitude: -23.5654749,
            longitude: -46.8051166,
            latitudeDelta: 0.05,
            longitudeDelta: 0.05
        })

        setDestination({
            latitude: location.latitude,
            longitude: location.longitude,
            latitudeDelta: 0.05,
            longitudeDelta: 0.05
        })

    }, [])


    return(
        <SafeAreaView style={styles.viewPrincipal}>
            <StatusBar style="light" backgroundColor={"#000000"}/>
            <MapView region={origin} showsIndoors={true} showsUserLocation={true} ref={mapEl} style={{flex: props.flex ? props.flex : 1}}>
                <MapViewDirections origin={origin} destination={destination} apikey={MAPS_KEY} strokeWidth={2}/>
            </MapView>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    viewPrincipal:{
        flex: 1,
        marginTop: StatusBar.currentHeight
    }
})