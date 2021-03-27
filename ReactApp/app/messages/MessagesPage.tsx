import React from 'react';
import {StyleSheet, View} from 'react-native';
import {Toolbar} from '../common/Toolbar';
import {appColors} from '../globalStyles';

export const MessagesPage = () => {
  return (
    <View style={styles.content}>
      <Toolbar title={'Komunikaty'} />
    </View>
  );
};

const styles = StyleSheet.create({
  content: {
    flex: 1,
    backgroundColor: appColors.white,
  },
});
