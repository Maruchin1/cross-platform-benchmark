import React from 'react';
import {Event} from '../model/Event';
import {Image, StyleSheet, Text, View} from 'react-native';
import {appColors, globalStyles} from '../globalStyles';

export const EventItem = ({event}: {event: Event}) => {
  return (
    <View style={styles.itemCard}>
      <View style={styles.eventImageContainer}>
        <Image style={styles.eventImage} source={{uri: event.imageUrl}} />
      </View>
      <View style={styles.itemContent}>
        <Text style={styles.eventName}>{event.name}</Text>
        <Text style={styles.eventDate}>{event.date}</Text>
      </View>
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
