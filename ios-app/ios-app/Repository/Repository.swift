//
//  Repository.swift
//  ios-app
//
//  Created by admin on 01/04/2021.
//

import Foundation

class Repository : RepositoryProtocol {
    private let webApi: WebApi
    private let localDatabase: LocalDatabase
    
    @Published var events: [Event]
    
    private var eventsPageNumber = 0
    
    init(webApi: WebApi, localDatabase: LocalDatabase) {
        self.webApi = webApi
        self.localDatabase = localDatabase
        events = []
        loadNextEventsPage()
    }
    
    func loadNextEventsPage() -> Void {
        webApi.getEventsPage(pageNumber: eventsPageNumber + 1, pageSize: 20) { loadedEvents in
            if !loadedEvents.isEmpty {
                self.localDatabase.insertEvents(events: loadedEvents)
                self.refreshEvents()
                self.eventsPageNumber += 1
            }
        }
    }
    
    private func refreshEvents() {
        let eventsFromDb = self.localDatabase.getAllEvents()
        DispatchQueue.main.async {
            self.events = eventsFromDb
        }
    }
}
