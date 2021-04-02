//
//  EventExpandedItem.swift
//  ios-app
//
//  Created by admin on 02/04/2021.
//

import SwiftUI
import URLImage

struct EventExpandedItem: View {
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
            .overlay(Rectangle().frame(width: nil, height: 2, alignment: .top).foregroundColor(Color("Grey400")), alignment: .top)
            .overlay(Rectangle().frame(width: nil, height: 2, alignment: .bottom).foregroundColor(Color("Grey400")), alignment: .bottom)
            VStack(alignment: .leading) {
                Text(event.name)
                    .font(.title2)
                Text(event.date)
                    .font(.caption)
                    .padding(.top, 4)
                Text("Miejsce: \(event.place)")
                    .font(.caption)
                    .padding(.top, 4)
                Text(event.description)
                    .font(.body)
                    .padding(.top, 12)
                Text("Galeria")
                    .font(.caption)
                    .padding(.top, 20)
                    .padding(.bottom, 12)
            }
            .frame(minWidth: /*@START_MENU_TOKEN@*/0/*@END_MENU_TOKEN@*/, maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .leading)
            .padding(.top, 24)
            .padding(.horizontal, 32)
            ScrollView(.horizontal) {
                HStack(spacing: 0) {
                    ForEach(event.galleryImagesUrls, id: \.self) { imageUrl in
                        GalleryItem(imageUrl: imageUrl)
                    }
                }
            }
            .frame(height: 200)
            .padding(.bottom, 32)
        }
        .frame(minWidth: /*@START_MENU_TOKEN@*/0/*@END_MENU_TOKEN@*/, maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .center)
    }
}
struct EventExpandedItem_Previews: PreviewProvider {
    static let event = Event(
        id: 1,
        imageUrl: "https://picsum.photos/200/300",
        name: "Lorem ipsum",
        date: "2021-03-11",
        place: "Lorem ipsum",
        description: "Lorem ipsum",
        galleryImagesUrls: [
            "https://picsum.photos/600/800",
            "https://picsum.photos/600/800",
            "https://picsum.photos/600/800",
        ]
    )
    
    static var previews: some View {
        EventExpandedItem(event: event)
    }
}
