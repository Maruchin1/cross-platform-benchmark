import React, {useCallback, useMemo, useRef, useState} from 'react';
import {
  ActivityIndicator,
  FlatList,
  LayoutAnimation,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import {appColors, globalStyles} from '../globalStyles';
import {useRepository} from '../repository/useRepository';
import {Event} from '../model/Event';
import {EventExpandedItem} from './EventExpandedItem';
import {EventCollapsedItem} from './EventCollapsedItem';
import {Toolbar} from '../common/Toolbar';

export const EventsPage = () => {
  const {events, loading, loadNextEventsPage} = useRepository();
  const [openedEventId, setOpenedEventId] = useState<number | null>(null);
  const eventsList = useRef<FlatList>(null);

  const listItems = useMemo(() => {
    return [null, null, ...events];
  }, [events]);

  const openEvent = useCallback((event: Event) => {
    LayoutAnimation.configureNext(LayoutAnimation.Presets.easeInEaseOut);
    setOpenedEventId(event.id);
  }, []);

  const closeOpenedEvent = useCallback(() => {
    LayoutAnimation.configureNext(LayoutAnimation.Presets.easeInEaseOut);
    setOpenedEventId(null);
  }, []);

  const scrollToIndex = (index: number) => {
    eventsList.current.scrollToIndex({
      index,
      animated: true,
      viewPosition: 0,
      viewOffset: 60,
    });
  };

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
    <FlatList
      style={styles.eventsList}
      ref={eventsList}
      data={listItems}
      renderItem={({item, index}) => {
        if (item instanceof Event) {
          if (item.id === openedEventId) {
            return (
              <View
                style={index === 2 ? styles.firstItem : {}}
                onLayout={() => setTimeout(() => scrollToIndex(index), 100)}>
                <EventExpandedItem event={item} onClose={closeOpenedEvent} />
              </View>
            );
          } else {
            return (
              <View style={index === 2 ? styles.firstItem : {}}>
                <EventCollapsedItem event={item} onClick={openEvent} />
              </View>
            );
          }
        } else if (index === 0) {
          return (
            <View style={styles.headline}>
              <Text style={styles.textHeadline}>
                25-lecie{'\n'}Wydziału Grafiki{'\n'}i Sztuki Mediów
              </Text>
            </View>
          );
        } else if (index === 1) {
          return <Toolbar title={'Wydarzenia'} />;
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
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
  },
  headline: {
    backgroundColor: appColors.white,
    paddingTop: 156,
    paddingBottom: 100,
    flexDirection: 'row',
    justifyContent: 'center',
  },
  textHeadline: {
    textAlign: 'center',
    ...globalStyles.textHeadline,
  },
  eventsList: {
    backgroundColor: appColors.white,
  },
  firstItem: {
    marginTop: 48,
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
