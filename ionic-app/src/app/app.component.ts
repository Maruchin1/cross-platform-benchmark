import {Component} from '@angular/core';
import {LocalDatabaseService} from './core/local-database.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  constructor(
    private localDatabaseService: LocalDatabaseService
  ) {
    localDatabaseService.initDatabase();
  }
}
