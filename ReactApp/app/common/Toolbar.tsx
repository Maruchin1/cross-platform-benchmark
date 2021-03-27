import React from 'react';
import {StyleSheet, Text, View} from 'react-native';
import {appColors, globalStyles} from '../globalStyles';

interface InputData {
  title: string;
}

export const Toolbar = ({title}: InputData) => {
  return (
    <View style={styles.toolbar}>
      <Text style={styles.textTitle}>{title}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  toolbar: {
    backgroundColor: appColors.white,
    height: 56,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
  },
  textTitle: {
    ...globalStyles.textTitle,
  },
});
