//
//  EventItemView.swift
//  ios-app
//
//  Created by admin on 01/04/2021.
//

import SwiftUI
import URLImage

struct EventCollapsedItem: View {
    var event: Event
    
    var body: some View {
        VStack {
            ZStack {
                Rectangle().frame(width: nil, height: 1, alignment: .top).foregroundColor(Color("Grey400"))
                Rectangle()
                    .fill(Color("Grey300"))
                URLImage(url: URL(string: event.imageUrl)!) { image in
                    image
                        .resizable()
                        .aspectRatio(CGSize(width: 4, height: 3), contentMode: .fit)
                }
            }
            .aspectRatio(CGSize(width: 4, height: 3), contentMode: .fit)
            .overlay(Rectangle().frame(width: nil, height: 2, alignment: .bottom).foregroundColor(Color("Grey400")), alignment: .bottom)
            VStack(alignment: .leading) {
                Text(event.name)
                    .font(.title2)
                    .foregroundColor(.primary)
                Text(event.date)
                    .font(.caption)
                    .foregroundColor(.secondary)
                    .padding(.top, 4.0)
            }
            .frame(minWidth: /*@START_MENU_TOKEN@*/0/*@END_MENU_TOKEN@*/, maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .leading)
            .padding()
        }
        .frame(minWidth: /*@START_MENU_TOKEN@*/0/*@END_MENU_TOKEN@*/, maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .center)
        .overlay(
            Rectangle()
                .stroke(Color("Grey400"), lineWidth: 2)
        )
    }
}

struct EventItemView_Previews: PreviewProvider {
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
        EventCollapsedItem(event: event)
    }
}
