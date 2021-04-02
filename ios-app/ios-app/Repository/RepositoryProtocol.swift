//
//  RepositoryProtocol.swift
//  ios-app
//
//  Created by admin on 02/04/2021.
//

import Foundation

protocol RepositoryProtocol : ObservableObject {
    var events: [Event] {get}
    
    func loadNextEventsPage() -> Void
}
