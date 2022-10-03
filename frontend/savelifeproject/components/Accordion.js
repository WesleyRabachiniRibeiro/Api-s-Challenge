import React, {useState} from 'react';
import { StyleSheet, Text, View, TouchableOpacity, Dimensions } from 'react-native';
import { Ionicons } from "@expo/vector-icons";
import Mapa from './Mapa';

const windowWidth = Dimensions.get('window').width;
const windowHeight = Dimensions.get('window').height;

export default function Accordion() {
  const [isActive, setIsActive] = useState(false);

  const handleClick = () => {
    setIsActive(!isActive);
  }

  return (
    <View style={styles.container}>
      <View style={styles.accordion}>
        <TouchableOpacity  style={styles.button} onPress={handleClick}>
        <Ionicons name={isActive ? 'chevron-down-outline' : 'chevron-up-outline'} color='#B9B9B9' size={45}/>
        </TouchableOpacity>
        <View style={[styles.accordionContent, isActive ? styles.active : styles.desactive]}>
          <View style={styles.accordionMap}>
            <Mapa/>
          </View>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white'
  },
  accordion: {
    backgroundColor: '#fff',
    position: 'absolute',
    bottom: 0,
    width: '100%',
    display: 'flex',
    alignItems: 'center',
    borderTopLeftRadius: 15,
    borderTopRightRadius: 15,
    paddingTop: 10
  },
  button: {
    paddingTop: 5,
    paddingBottom: 15,
    width: windowWidth,
    alignItems: 'center'
  }, 
  accordionContent: {
    width: windowWidth,
    overflow: 'hidden',
    transition: 'all .2s ease-in-out',
  },
  accordionMap: {
    backgroundColor: 'white',
    width: windowWidth,
    height: windowHeight * 0.9
  },
  desactive: {
    height: 0
  },
  active: {
    height: windowHeight * 0.85
  }
});