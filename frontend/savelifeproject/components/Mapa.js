import React, { useEffect, useState, useRef, createRef } from 'react';
import {StyleSheet, SafeAreaView, StatusBar } from 'react-native';
import MapView, {Marker} from 'react-native-maps';
import axios from 'axios';
import { GlobalContext } from '../components/GlobalContext';
import MapViewDirections from 'react-native-maps-directions';
import {ref, onValue} from "firebase/database"
import { Ionicons, FontAwesome5 } from "@expo/vector-icons";

const {MAPS_KEY}  = process.env;
export default function Mapa(props){
    const global = React.useContext(GlobalContext)
    let location = global.coords
    let database = global.database
    let ambulanceCoords;
    const radius = 100*1000
    const baseUrl = `https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${location.latitude},${location.longitude}&radius=${radius}&type=hospital&key=${MAPS_KEY}`
    const source = axios.CancelToken.source();

    const mapEl = useRef();

    const dbRef = ref(database, 'users/' + "ambulancia");

    onValue(dbRef, (snapshot) => {
      const data = snapshot.val();
      ambulanceCoords = {
        latitude: data.coords.latitude,
        longitude: data.coords.longitude,
        latitudeDelta: 0.05,
        longitudeDelta: 0.05
    }
    console.log(ambulanceCoords.latitude)
    console.log(ambulanceCoords.longitude)
    });

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
            baseUrl, {headers: { "Content-Type": "application/json"}, cancelToken: source.token})
        .then((res) => {
            filterHospital(res.data.results)
            setDestination({
                latitude: ambulanceCoords.latitude,
                longitude: ambulanceCoords.longitude,
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

    return(
        <SafeAreaView style={styles.viewPrincipal}>
            <StatusBar style="light" backgroundColor={"#000000"}/>
            <MapView region={origin} followsUserLocation={true} showsUserLocation={true} ref={mapEl} style={{flex: props.flex ? props.flex : 1}}>
                { !props.hasRequest &&
                    Object.values(hospitals).map(value => {
                        return <Marker key={value.name} coordinate={{latitude: value.geometry.location.lat, longitude: value.geometry.location.lng}} title={value.name} pinColor={"#6914FF"}>
                            <FontAwesome5 style={{color: '#6914FF'}} name="hospital-alt" size={20}/>
                        </Marker>
                        }
                    )
                }
                {props.hasRequest && 
                <>
                    <MapViewDirections origin={origin} destination={destination} apikey={MAPS_KEY}/>
                    <Marker coordinate={destination}>
                        <FontAwesome5 style={{color: '#6914FF'}} name="ambulance" size={20}/>
                    </Marker>
                </>
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