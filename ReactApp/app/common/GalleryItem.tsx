import React from 'react';
import {Image, StyleSheet, View} from 'react-native';
import {appColors} from '../globalStyles';

interface InputData {
  imageUrl: string;
}

export const GalleryItem = ({imageUrl}: InputData) => {
  return (
    <View style={styles.card}>
      <Image style={styles.image} source={{uri: imageUrl}} />
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    borderRadius: 0,
    borderColor: appColors.grey400,
    borderWidth: 2,
  },
  image: {
    width: 150,
    aspectRatio: 3 / 4,
    backgroundColor: appColors.grey300,
  },
});
