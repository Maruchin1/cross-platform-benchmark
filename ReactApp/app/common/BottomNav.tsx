import React, {useState} from 'react';
import {StyleSheet, Text, TouchableHighlight, View} from 'react-native';
import {appColors, globalStyles} from '../globalStyles';

export type BottomNavOption = 'events' | 'messages' | 'department' | 'timeline';

interface InputData {
  onOptionChange: (option: BottomNavOption) => void;
}

export const BottomNav = ({onOptionChange}: InputData) => {
  const [selectedOption, setSelectedOption] = useState<BottomNavOption>(
    'events',
  );

  const onOptionClicked = (option: BottomNavOption) => {
    setSelectedOption(option);
    onOptionChange(option);
  };

  const Option = ({option, text}: {option: BottomNavOption; text: string}) => {
    return (
      <TouchableHighlight
        activeOpacity={0.6}
        underlayColor={appColors.grey400}
        style={styles.option}
        onPress={() => onOptionClicked(option)}>
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
