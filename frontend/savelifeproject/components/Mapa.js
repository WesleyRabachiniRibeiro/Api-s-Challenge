import React, { useEffect, useState, useRef } from 'react';
import {StyleSheet, SafeAreaView, StatusBar } from 'react-native';
import MapView, {Marker} from 'react-native-maps';
import axios from 'axios';
import MapViewDirections from 'react-native-maps-directions';
import { GlobalContext } from '../components/GlobalContext';

const {MAPS_KEY}  = process.env;
export default function Mapa(props){
    const global = React.useContext(GlobalContext)
    let location = global.coords
    const radius = 50*1000
    const baseUrl = `https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${location.latitude},${location.longitude}&radius=${radius}&type=hospital&key=${MAPS_KEY}`
    const source = axios.CancelToken.source();

    const mapEl=useRef(null);
    const [origin, setOrigin] = useState(null)
    const [destination, setDestination] = useState(null)
    const [hospitals, setHospitals] = useState([])
    const [markers, setMarkers] = useState([]);

    const filterHospital = (hospitalsList) =>{
        let hospitalList = []
        hospitalsList.map((hospital, key) => {
            if(hospital.name.toLowerCase().includes("hospital")){
                hospitalList.push(hospital);
            }
        })
        setHospitals(hospitalList)
    }

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
            filterHospital(res.data.results)
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
                {
                    Object.values(hospitals).map(value => {
                        return <Marker key={value.name} coordinate={{latitude: value.geometry.location.lat, longitude: value.geometry.location.lng}} title={value.name} pinColor={"#6914FF"}/>
                        }
                    )
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