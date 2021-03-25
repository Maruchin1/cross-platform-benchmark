import React, {useEffect} from 'react';
import {ActivityIndicator, FlatList, StyleSheet, View} from 'react-native';
import {appColors, globalStyles} from '../globalStyles';
import {useRepository} from '../repository/useRepository';
import {EventItem} from './EventItem';

export const EventsPage = () => {
  const {isInitialized, events, loading, loadNextEventsPage} = useRepository();

  useEffect(() => {
    console.log('## useEffect, isInitialized: ', isInitialized);
    if (isInitialized) {
      loadNextEventsPage();
    }
  }, [isInitialized]);

  const renderLoadingIndicator = () => {
    return (
      <View style={styles.loadingIndicator}>
        {loading ? (
          <ActivityIndicator color={appColors.grey400} style={{margin: 15}} />
        ) : null}
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <FlatList
        data={events}
        renderItem={info => <EventItem event={info.item} />}
        keyExtractor={item => item.id.toString()}
        onEndReachedThreshold={0}
        onEndReached={() => loadNextEventsPage()}
        ListFooterComponent={renderLoadingIndicator}
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
  loadingIndicator: {
    padding: 16,
    justifyContent: 'center',
    alignItems: 'center',
    flexDirection: 'row',
  },
});
