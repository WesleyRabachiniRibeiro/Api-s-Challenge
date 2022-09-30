import React, { useEffect, useState, useRef, useCallback } from 'react'
import {StyleSheet, SafeAreaView, StatusBar, View } from 'react-native'
import MapView, {Marker} from 'react-native-maps'
import axios from 'axios';
import MapViewDirections from 'react-native-maps-directions';
import { GlobalContext } from '../components/GlobalContext';


const { MAPS_KEY } = process.env;

export default function Mapa(props){
    const global = React.useContext(GlobalContext)
    let location = global.coords
    const radius = 1*1000
    const baseUrl = `https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${location.latitude},${location.longitude}&radius=${radius}&type=hospital&key=${MAPS_KEY}`
    const source = axios.CancelToken.source();


    const mapEl=useRef(null);
    const [origin, setOrigin] = useState(null)
    const [destination, setDestination] = useState(null)
    const [hospitals, setHospitals] = useState([])
    const [markers, setMarkers] = useState([]);
            
    useEffect( () => {
        setOrigin({
            latitude: location.latitude,
            longitude: location.longitude,
            latitudeDelta: 0.05,
            longitudeDelta: 0.05
        })  
    }, [])

    useEffect(() => {
        axiosGet()
    }, [])

    const axiosGet = () => {
        axios.get(
            baseUrl,
        {
            headers: { "Content-Type": "application/json"},
            cancelToken: source.token
        })
        .then((res) => {
            setHospitals(res.data.results)
            setDestination({
                latitude: res.data.results[0].geometry.location.lat,
                longitude: res.data.results[0].geometry.location.lng,
                latitudeDelta: 0.05,
                longitudeDelta: 0.05
            })
            setMarkers({
                lat: res.data.results[0].geometry.location.lat,
                lng: res.data.results[0].geometry.location.lng,
            })
            console.log(`destination ${destination}`)
            
        })
        .catch((err) => {
            console.error(err);
        });
        
    }
    console.log(`destination outside ${destination}`)
    return(
        <SafeAreaView style={styles.viewPrincipal}>
            <StatusBar style="light" backgroundColor={"#000000"}/>
            <MapView initialRegion={origin} showsUserLocation={true} ref={mapEl} style={{flex: props.flex ? props.flex : 1}}>
                {destination && 
                <MapViewDirections
                    origin={origin}
                    destination={destination}
                    apikey={MAPS_KEY}
                    strokeWidth={3}
                    onReady={result=>{
                        mapEl.current.fitToCoordinates(
                            result.coordinates,{
                                edgePadding:{
                                    top:50,
                                    bottom:50,
                                    left:50,
                                    right:50
                                }
                            }
                        )
                    }}
                />
                }
                {destination !== null &&
                    <Marker coordinate={{latitude: destination.latitude, longitude: destination.longitude}} title={"Hospital"}/>
                }
            </MapView>
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