import React, { useEffect, useState, useRef } from 'react';
import {StyleSheet, SafeAreaView, StatusBar } from 'react-native';
import MapView from 'react-native-maps';
import { GlobalContext } from '../components/GlobalContext';

export default function MapaAmbulancia(props){
    const global = React.useContext(GlobalContext)
    let location = global.coords

    const mapEl=useRef(null);

    const [origin, setOrigin] = useState(null)

    useEffect( () => {
        setOrigin({
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