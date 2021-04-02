//
//  EventItem.swift
//  ios-app
//
//  Created by admin on 02/04/2021.
//

import SwiftUI

struct EventItem: View {
    var event: Event
    var isExpanded: Bool
    
    var body: some View {
        if isExpanded {
            EventExpandedItem(event: event)
        } else {
            EventCollapsedItem(event: event)
                .padding(.horizontal, 16)
                .padding(.bottom, 24)
        }
    }
}

struct EventItem_Previews: PreviewProvider {
    static let event = Event(
        id: 1,
        imageUrl: "https://picsum.photos/200/300",
        name: "Test",
        date: "2021-03-11",
        place: "",
        description: "",
        galleryImagesUrls: []
    )
    
    static var previews: some View {
        EventItem(event: event, isExpanded: false)
    }
}
