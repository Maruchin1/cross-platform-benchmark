import React, {useEffect} from 'react';
import {useState} from 'react';
import {BottomNav, BottomNavOption} from './common/BottomNav';
import {SafeAreaView, StatusBar, StyleSheet, View} from 'react-native';
import {EventsPage} from './events/EventsPage';
import {appColors} from './globalStyles';
import {MessagesPage} from './messages/MessagesPage';
import {DepartmentPage} from './department/DepartmentPage';
import {TimelinePage} from './timeline/TimelinePage';
import {LocalDatabaseContext} from './repository/LocalDatabase';

export const Tabs = () => {
  const [option, setOption] = useState<BottomNavOption>('events');

  useEffect(() => {
    renderCurrentPage();
  }, []);

  const renderCurrentPage = () => {
    switch (option) {
      case 'events':
        return <EventsPage />;
      case 'messages':
        return <MessagesPage />;
      case 'department':
        return <DepartmentPage />;
      case 'timeline':
        return <TimelinePage />;
    }
  };

  return (
    <LocalDatabaseContext.Provider value={{events: []}}>
      <SafeAreaView style={styles.container}>
        <StatusBar
          backgroundColor={appColors.white}
          barStyle={'dark-content'}
        />
        <View style={styles.container}>{renderCurrentPage()}</View>
        <BottomNav onOptionChange={setOption} />
      </SafeAreaView>
    </LocalDatabaseContext.Provider>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: appColors.white,
  },
});
