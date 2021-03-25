import React, {useCallback, useMemo, useState} from 'react';
import {
  ActivityIndicator,
  FlatList,
  StatusBar,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import {appColors, globalStyles} from '../globalStyles';
import {useRepository} from '../repository/useRepository';
import {Event} from '../model/Event';
import {BottomNav} from '../common/BottomNav';
import {EventExpandedItem} from './EventExpandedItem';
import {EventItem} from './EventItem';

export const EventsPage = () => {
  const {events, loading, loadNextEventsPage} = useRepository();
  const [openedEventId, setOpenedEventId] = useState<number | null>(null);

  const listItems = useMemo(() => {
    return [null, null, ...events];
  }, [events]);

  const openEvent = useCallback((event: Event) => {
    setOpenedEventId(event.id);
  }, []);

  const closeOpenedEvent = useCallback(() => {
    setOpenedEventId(null);
  }, []);

  const renderLoadingIndicator = () => {
    return (
      <View style={styles.loadingIndicator}>
        {loading ? (
          <ActivityIndicator
            color={appColors.grey400}
            style={styles.activityIndicator}
          />
        ) : null}
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <StatusBar backgroundColor={appColors.white} barStyle={'dark-content'} />
      <FlatList
        data={listItems}
        renderItem={({item, index}) => {
          if (item instanceof Event) {
            if (item.id === openedEventId) {
              return (
                <EventExpandedItem event={item} onClose={closeOpenedEvent} />
              );
            } else {
              return <EventItem event={item} onClick={openEvent} />;
            }
          } else if (index === 0) {
            return (
              <View style={styles.headline}>
                <Text style={styles.textHeadline}>
                  25-lecie Wydziału{'\n'}Grafiki i Sztuki{'\n'}Mediów
                </Text>
              </View>
            );
          } else if (index === 1) {
            return (
              <View style={styles.toolbar}>
                <Text style={styles.textTitle}>Wydarzenia</Text>
              </View>
            );
          }
        }}
        keyExtractor={(item, index) => {
          if (item instanceof Event) {
            return (item.id + index).toString();
          } else {
            return index.toString();
          }
        }}
        stickyHeaderIndices={[1]}
        onEndReachedThreshold={0}
        onEndReached={() => loadNextEventsPage()}
        ListFooterComponent={renderLoadingIndicator}
      />
      <BottomNav />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
  },
  headline: {
    backgroundColor: appColors.white,
    paddingTop: 128,
    paddingBottom: 82,
    flexDirection: 'row',
    justifyContent: 'center',
  },
  textHeadline: {
    textAlign: 'center',
    ...globalStyles.textHeadline,
  },
  toolbar: {
    backgroundColor: appColors.white,
    height: 60,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
  },
  textTitle: {
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
  activityIndicator: {
    margin: 15,
  },
});
