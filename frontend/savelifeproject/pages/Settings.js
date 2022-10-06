import React, { useEffect, useState } from 'react';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, TouchableOpacity, Image } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { GlobalContext } from '../components/GlobalContext';

export default function Settings(props) {

  const global = React.useContext(GlobalContext)

    return (
      <View style={styles.container}>
        <View style={styles.profile}>
          <TouchableOpacity>
            <Image
              style={styles.img}
              source={require('../assets/person.png')}
              />
          </TouchableOpacity>
          <View style={styles.informations}>
            <Text style={styles.title}>{global.name}</Text>
            <Text>CPF: {global.cpf}</Text>
          </View>
        </View>
        <View style={styles.cardContainer}>
          <TouchableOpacity style={styles.card}>
            <Ionicons name='key' style={styles.cardIcon} />
            <View>
              <Text style={styles.cardTitle}>Conta</Text>
              <Text style={styles.cardSubTitle}>Configurações relacionadas a conta.</Text>
            </View>
          </TouchableOpacity>
          <TouchableOpacity style={styles.card}>
            <Ionicons name='information-circle' style={styles.cardIcon}/>
            <View>
              <Text style={styles.cardTitle}>Informações</Text>
              <Text style={styles.cardSubTitle}>Informações gerais do app</Text>
            </View>
          </TouchableOpacity>
          <TouchableOpacity style={styles.card}>
            <Ionicons name='lock-closed' style={styles.cardIcon}/>
            <View>
              <Text style={styles.cardTitle}>Privacidade</Text>
              <Text style={styles.cardSubTitle}>Alteração das configurações de Privacidade.</Text>
            </View>
          </TouchableOpacity>
        </View>
      </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingTop: 50,
        paddingHorizontal: 25,
        backgroundColor: '#FCFCFC',
      },
      profile: {
        flexDirection: "row",
        alignItems: "center",
        marginBottom: 20
      },
      informations: {
        marginLeft: 10
      },
      title: {
        fontSize: 24,
        fontWeight: "bold"
      },
      img: {
        width: 60,
        height: 60,
        borderRadius: 50,
        resizeMode: "cover"
      },
      cardContainer: {
        flex: 1,
      },
      card: {
        flexDirection: "row",
        alignItems: "center",
        backgroundColor: "#D9D9D9",
        padding: 10,
        height: 100,
        marginBottom: 30,
        borderRadius: 20
      },
      cardIcon: {
        fontSize: 30,
        marginRight: 10
      },
      cardTitle: {
        fontSize: 24,
        fontWeight: "bold"
      },
      cardSubTitle: {
        fontSize: 18,
      }
})