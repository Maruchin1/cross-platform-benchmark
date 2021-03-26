import React, {useMemo} from 'react';
import {Event} from '../model/Event';
import {Image, ScrollView, StyleSheet, Text, View} from 'react-native';
import {appColors, globalStyles} from '../globalStyles';
import {GalleryItem} from '../common/GalleryItem';

interface InputData {
  event: Event;
  onClose: () => void;
}

export const EventExpandedItem = ({event}: InputData) => {
  const galleryImagesUrls: string[] = useMemo(() => {
    return JSON.parse(event.galleryImagesUrls);
  }, [event]);

  return (
    <View>
      <View style={styles.imageContainer}>
        <Image style={styles.image} source={{uri: event.imageUrl}} />
      </View>
      <View style={styles.contentContainer}>
        <Text style={styles.name}>{event.name}</Text>
        <Text style={styles.date}>{event.date}</Text>
        <Text style={styles.place}>Miejsce: {event.place}</Text>
        <Text style={styles.description}>{event.description}</Text>
        <Text style={styles.labelGallery}>Galeria</Text>
      </View>
      <View style={styles.galleryContainer}>
        <ScrollView horizontal={true}>
          {galleryImagesUrls.map(imageUrl => (
            <GalleryItem key={imageUrl} imageUrl={imageUrl} />
          ))}
        </ScrollView>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  imageContainer: {
    borderTopWidth: 2,
    borderTopColor: appColors.grey400,
    borderBottomWidth: 2,
    borderBottomColor: appColors.grey400,
  },
  image: {
    backgroundColor: appColors.grey300,
    aspectRatio: 4 / 3,
  },
  contentContainer: {
    paddingTop: 16,
    paddingHorizontal: 32,
  },
  name: {
    ...globalStyles.textTitle,
  },
  date: {
    ...globalStyles.textCaption,
    marginTop: 8,
  },
  place: {
    ...globalStyles.textCaption,
    marginTop: 8,
  },
  description: {
    ...globalStyles.textBody,
    marginTop: 16,
  },
  labelGallery: {
    ...globalStyles.textCaption,
    marginTop: 16,
  },
  galleryContainer: {
    paddingTop: 16,
    paddingBottom: 32,
  },
});
