//
//  Event.swift
//  ios-app
//
//  Created by admin on 01/04/2021.
//

import Foundation

struct Event: Hashable, Codable, Identifiable {
    var id: Int
    var imageUrl: String
    var name: String
    var date: String
    var place: String
    var description: String
    var galleryImagesUrls: [String]
}
