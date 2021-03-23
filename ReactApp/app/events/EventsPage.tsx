import React, {useEffect} from 'react';
import {FlatList, StyleSheet, View} from 'react-native';
import {globalStyles} from '../globalStyles';
import {useRepository} from '../repository/useRepository';
import {EventItem} from './EventItem';

export const EventsPage = () => {
  const repository = useRepository();

  useEffect(() => {
    repository.loadNextEventsPage();
  }, []);

  return (
    <View style={styles.container}>
      <FlatList
        data={repository.events}
        renderItem={info => <EventItem event={info.item} />}
        keyExtractor={item => item.id.toString()}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  appBar: {
    flexDirection: 'column',
  },
  textHeadline: {
    marginTop: 128,
    marginBottom: 64,
    textAlign: 'center',
    ...globalStyles.textHeadline,
  },
  textTitle: {
    textAlign: 'center',
    position: 'absolute',
    bottom: 8,
    left: 0,
    right: 0,
    ...globalStyles.textTitle,
  },
  scrollContainer: {
    padding: 16,
  },
  title: {
    fontSize: 24,
  },
});
