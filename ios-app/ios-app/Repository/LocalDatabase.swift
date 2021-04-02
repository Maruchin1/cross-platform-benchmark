//
//  LocalDatabase.swift
//  ios-app
//
//  Created by admin on 02/04/2021.
//

import Foundation
import SQLite

class LocalDatabase {
    
    private let eventsTable = Table("events")
    private let id = Expression<Int>("id")
    private let imageUrl = Expression<String>("imageUrl")
    private let name = Expression<String>("name")
    private let date = Expression<String>("date")
    private let place = Expression<String>("place")
    private let description = Expression<String>("description")
    private let galleryImagesUrls = Expression<String>("galleryImagesUrls")
    
    private var db: Connection?
    
    init() {
        do {
            db = try Connection()
            try db!.run(eventsTable.create {t in
                t.column(id, primaryKey: true)
                t.column(imageUrl)
                t.column(name)
                t.column(date)
                t.column(place)
                t.column(description)
                t.column(galleryImagesUrls)
            })
            try db!.run(eventsTable.delete())
        } catch {
            db = nil
        }
    }
    
    func insertEvents(events: [Event]) -> Void {
        if db != nil {
            for event in events {
                let insert = eventsTable.insert(
                    id <- event.id,
                    imageUrl <- event.imageUrl,
                    name <- event.name,
                    date <- event.date,
                    place <- event.place,
                    description <- event.description,
                    galleryImagesUrls <- event.galleryImagesUrls.joined(separator: ",")
                )
                do {
                    try db?.run(insert)
                } catch {
                    
                }
            }
        }
    }
    
    func getAllEvents() -> [Event] {
        var result: [Event] = []
        do {
            for eventRow in try db!.prepare(eventsTable) {
                let event = Event(
                    id: eventRow[id],
                    imageUrl: eventRow[imageUrl],
                    name: eventRow[name],
                    date: eventRow[date],
                    place: eventRow[place],
                    description: eventRow[description],
                    galleryImagesUrls: eventRow[galleryImagesUrls].components(separatedBy: ",")
                )
                result.append(event)
            }
        } catch {
            
        }
        return result
    }
}
