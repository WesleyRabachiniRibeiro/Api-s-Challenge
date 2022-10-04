import React, { useEffect, useState, useRef, createRef } from 'react';
import {StyleSheet, SafeAreaView, StatusBar } from 'react-native';
import MapView, {Marker} from 'react-native-maps';
import axios from 'axios';
import { GlobalContext } from '../components/GlobalContext';
import MapViewDirections from 'react-native-maps-directions';

const {MAPS_KEY}  = process.env;
export default function Mapa(props){
    const global = React.useContext(GlobalContext)
    let location = global.coords
    const radius = 100*1000
    const baseUrl = `https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${location.latitude},${location.longitude}&radius=${radius}&type=hospital&key=${MAPS_KEY}`
    const source = axios.CancelToken.source();

    const mapEl = useRef();

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
        console.log(hospitalsList)
        console.log(hospitalsList.length)
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
        })
        .catch((err) => {
            console.error(err);
        });
        
    }

    const goToMyLocation = async () => {
        mapEl.current.animateCamera({center: {"latitude":location.latitude, "longitude": location.longitude}});
}

    return(
        <SafeAreaView style={styles.viewPrincipal}>
            <StatusBar style="light" backgroundColor={"#000000"}/>
            <MapView region={origin} followsUserLocation={true} showsUserLocation={true} ref={mapEl} style={{flex: props.flex ? props.flex : 1}}>
                {
                    Object.values(hospitals).map(value => {
                        return <Marker key={value.name} coordinate={{latitude: value.geometry.location.lat, longitude: value.geometry.location.lng}} title={value.name} pinColor={"#6914FF"}/>
                        }
                    )
                }
                {<MapViewDirections origin={origin} destination={destination} apikey={MAPS_KEY}/>}
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