//
//  WebApi.swift
//  ios-app
//
//  Created by admin on 02/04/2021.
//

import Foundation

class WebApi {
    private let baseUrl = "http://192.168.8.157:8080"
    
    func getEventsPage(pageNumber: Int, pageSize: Int, onResult: @escaping ([Event]) -> Void) {
        let url = URL(string: baseUrl + "/events/page?pageNumber=\(pageNumber)&pageSize=\(pageSize)")!
        let session = URLSession.shared
        let task = session.dataTask(with: url) { data, response, error in
            do {
                let array = try JSONDecoder().decode([Event].self, from: data!)
                onResult(array)
            } catch {
                print("JSON error: \(error.localizedDescription)")
            }
        }
        task.resume()
    }
}
