import React from 'react';
import {Event} from '../model/Event';
import {Image, StyleSheet, Text, TouchableOpacity, View} from 'react-native';
import {appColors, globalStyles} from '../globalStyles';

interface InputData {
  event: Event;
  onClick: (event: Event) => void;
}

export const EventCollapsedItem = ({event, onClick}: InputData) => {
  return (
    <View style={styles.itemCard}>
      <TouchableOpacity activeOpacity={0.6} onPress={() => onClick(event)}>
        <View style={styles.cardContent}>
          <View style={styles.eventImageContainer}>
            <Image style={styles.eventImage} source={{uri: event.imageUrl}} />
          </View>
          <View style={styles.itemContent}>
            <Text style={styles.eventName}>{event.name}</Text>
            <Text style={styles.eventDate}>{event.date}</Text>
          </View>
        </View>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  itemCard: {
    marginHorizontal: 16,
    marginBottom: 24,
    borderWidth: 2,
    borderColor: appColors.grey400,
  },
  cardContent: {
    flex: 1,
  },
  eventImageContainer: {
    borderBottomWidth: 2,
    borderBottomColor: appColors.grey400,
  },
  eventImage: {
    aspectRatio: 4 / 3,
    backgroundColor: appColors.grey300,
  },
  itemContent: {
    padding: 20,
  },
  eventName: {
    marginBottom: 8,
    ...globalStyles.textTitle,
  },
  eventDate: {
    ...globalStyles.textCaption,
  },
});
