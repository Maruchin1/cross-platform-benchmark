//
//  Repository.swift
//  ios-app
//
//  Created by admin on 01/04/2021.
//

import Foundation

class Repository : ObservableObject {
    
    @Published var events: [Event]
    
    private var eventsPageNumber = 0
    
    init() {
        events = [
            Repository.makeEventMock(id: 1),
            Repository.makeEventMock(id: 2),
            Repository.makeEventMock(id: 3),
            Repository.makeEventMock(id: 4)
        ]
    }
    
    func loadNextEventsPage() -> Void {
        
    }
    
    private static func makeEventMock(id: Int) -> Event {
        return Event(
            id: id,
            imageUrl: "https://picsum.photos/800/600",
            name: "Lorem ipsum",
            date: "Lorem ipsum",
            place: "",
            description: "",
            galleryImagesUrls: []
        )
    }
}
