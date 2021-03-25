import React, {useState} from 'react';
import {StyleSheet, Text, TouchableHighlight, View} from 'react-native';
import {appColors, globalStyles} from '../globalStyles';

export type BottomNavOption = 'events' | 'messages' | 'department' | 'timeline';

export const BottomNav = () => {
  const [selectedOption, setSelectedOption] = useState<BottomNavOption>(
    'events',
  );

  const Option = ({option, text}: {option: BottomNavOption; text: string}) => {
    return (
      <TouchableHighlight
        activeOpacity={0.6}
        underlayColor={appColors.grey400}
        style={styles.option}
        onPress={() => setSelectedOption(option)}>
        <Text
          style={
            option === selectedOption
              ? styles.optionTextSelected
              : styles.optionText
          }>
          {text}
        </Text>
      </TouchableHighlight>
    );
  };

  return (
    <View style={styles.container}>
      <Option option={'events'} text={'wydarzenia'} />
      <Option option={'messages'} text={'komuniakty'} />
      <Option option={'department'} text={'wydział'} />
      <Option option={'timeline'} text={'25 lat'} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    height: 60,
    flexDirection: 'row',
    backgroundColor: appColors.grey300,
    borderTopColor: appColors.grey400,
    borderTopWidth: 2,
  },
  option: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  optionText: {
    ...globalStyles.textCaption,
  },
  optionTextSelected: {
    ...globalStyles.textCaption,
    fontWeight: 'bold',
  },
});
