//
//  RepositoryMock.swift
//  ios-app
//
//  Created by admin on 02/04/2021.
//

import Foundation

class RepositoryMock : RepositoryProtocol {
    @Published var events: [Event]
    
    init() {
        events = [
            RepositoryMock.makeEventMock(id: 1),
            RepositoryMock.makeEventMock(id: 2),
            RepositoryMock.makeEventMock(id: 3),
            RepositoryMock.makeEventMock(id: 4)
        ]
    }
    
    func loadNextEventsPage() {
        
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
