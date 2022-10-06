import React from 'react'
import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import { Ionicons } from "@expo/vector-icons";
import { GlobalContext } from '../components/GlobalContext';

export default function App() {
    
    const global = React.useContext(GlobalContext)

  return (
    <View style={styles.container}>
      <TouchableOpacity style={styles.backButton}>
        <Ionicons name="arrow-back-outline" size={40}/>
      </TouchableOpacity>

      <View style={styles.profile}>
        <Ionicons name="person-circle-outline" size={80}/>
        <View>
          <Text style={styles.profileName}>Daniel Cariatte</Text>
          <Text style={styles.profilePhone}>(11) 94037-2773</Text>
        </View>
      </View>

      <View style={styles.contentCard}>
        <Text style={styles.contentTitle}>Informações da ocorrencia:</Text>
        <Text style={styles.contentText}>Meu pai caiu da escada e não esta sentindo suas pernas me ajuda</Text>
      </View>

      <View style={styles.contentCard}>
        <Text style={styles.contentTitle}>Local: </Text>
        <Text style={styles.contentText}>Av. Lins de Vasconcelos, 1222 - Aclimação, São Paulo - SP, 01538-001</Text>
      </View>

      <View style={styles.waitContainer}>
        <Text style={styles.waitTime}>5 min</Text>
        <Text style={styles.waitDistance}>1 km * 20:00</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    paddingHorizontal: 20,
  },
  backButton: {
    paddingTop: 20,
    paddingLeft: 5
  },
  profile: {
    marginTop: 30,
    flexDirection: 'row',
    alignItems: 'center'
  },
  profileName: {
    marginLeft: 10,
    fontSize: 20,
    fontWeight: 'bold'
  },
  profilePhone: {
    marginLeft: 10,
    fontSize: 13
  },
  contentCard: {
    backgroundColor: '#F1F1F1',
    marginTop: 40,
    paddingVertical: 30,
    paddingHorizontal: 30,
    borderRadius: 15
  },
  contentTitle: {
    fontSize: 20,
    marginBottom: 15,
    fontWeight: "700"
  },
  contentText: {
    fontSize: 13,
    marginBottom: 7
  },
  waitContainer: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    width: '100%',
    alignItems: 'center',
    backgroundColor: '#373737',
    padding: 20
  },
  waitTime: {
    fontSize: 25,
    color: '#fff',
    fontWeight: 500,
    marginBottom: 5
  },
  waitDistance: {
    fontSize: 15,
    color: '#fff'
  }
});