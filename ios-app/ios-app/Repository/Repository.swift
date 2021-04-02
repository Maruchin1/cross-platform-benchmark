//
//  Repository.swift
//  ios-app
//
//  Created by admin on 01/04/2021.
//

import Foundation

class Repository : RepositoryProtocol {
    private let webApi: WebApi
    
    @Published var events: [Event]
    
    private var eventsPageNumber = 0
    
    init(webApi: WebApi) {
        self.webApi = webApi
        events = []
        loadNextEventsPage()
    }
    
    func loadNextEventsPage() -> Void {
        webApi.getEventsPage(pageNumber: eventsPageNumber + 1, pageSize: 20) { loadedEvents in
            if !loadedEvents.isEmpty {
                DispatchQueue.main.async {
                    self.events.append(contentsOf: loadedEvents)
                }
                self.eventsPageNumber += 1
            }
        }
    }
}
