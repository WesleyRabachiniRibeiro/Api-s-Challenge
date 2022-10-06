import React from 'react'
import { View, StyleSheet, Dimensions } from 'react-native'
import Mapa from '../components/Mapa'

const windowWidth = Dimensions.get('window').width;
const windowHeight = Dimensions.get('window').height;

export default function Map(){
    return(
        <View style={styles.container}>
            <Mapa/>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
      backgroundColor: '#FCFCFC',
      flexGrow: 1,
    }
  });
  