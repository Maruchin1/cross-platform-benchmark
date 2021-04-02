//
//  GalleryItem.swift
//  ios-app
//
//  Created by admin on 02/04/2021.
//

import SwiftUI
import URLImage

struct GalleryItem: View {
    var imageUrl: String
    
    var body: some View {
        ZStack {
            Rectangle()
                .fill(Color("Grey300"))
            URLImage(url: URL(string: imageUrl)!) { image in
                image
                    .resizable()
                    .aspectRatio(CGSize(width: 3, height: 4), contentMode: .fit)
            }
        }
        .aspectRatio(CGSize(width: 3, height: 4), contentMode: .fit)
        .overlay(
            Rectangle()
                .stroke(Color("Grey400"), lineWidth: 2)
        )
    }
}

struct GalleryItem_Previews: PreviewProvider {
    static var previews: some View {
        GalleryItem(imageUrl: "https://picsum.photos/600/800")
    }
}
