import {StyleSheet, View} from 'react-native';
import {Toolbar} from '../common/Toolbar';
import React from 'react';
import {appColors} from '../globalStyles';

export const DepartmentPage = () => {
  return (
    <View style={styles.content}>
      <Toolbar title={'Wydział'} />
    </View>
  );
};

const styles = StyleSheet.create({
  content: {
    flex: 1,
    backgroundColor: appColors.white,
  },
});
