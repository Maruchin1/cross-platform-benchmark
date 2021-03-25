import React, {useMemo} from 'react';
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
import {EventItem} from './EventItem';
import {Event} from '../model/Event';
import {BottomNav} from '../common/BottomNav';

export const EventsPage = () => {
  const {events, loading, loadNextEventsPage} = useRepository();

  const listItems = useMemo(() => {
    return [null, null, ...events];
  }, [events]);

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
      <StatusBar backgroundColor={appColors.white} barStyle={'dark-content'} />
      <FlatList
        data={listItems}
        renderItem={({item, index}) => {
          if (item instanceof Event) {
            return <EventItem event={item} />;
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
});
