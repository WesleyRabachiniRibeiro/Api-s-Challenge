import React from 'react'
import { View, Text, TouchableOpacity } from 'react-native'

export default function PhoneItem(props) {
    return (
        <View style={{flexDirection: 'row', marginHorizontal: 20, marginVertical: 10,}}>
            <Text>{props.ddd} {props.number}</Text>
            <TouchableOpacity onPress={props.onEditar}>
                <Text>Editar</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={props.onDeletar}>
                <Text>Deletar</Text>
            </TouchableOpacity>
        </View>
    )
}