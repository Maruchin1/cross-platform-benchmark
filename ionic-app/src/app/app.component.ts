import {Component} from '@angular/core';
import {LocalDatabaseService} from './core/local-database.service';
import {Plugins, StatusBarStyle} from '@capacitor/core';

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

    Plugins.StatusBar.setBackgroundColor({color: '#ffffff'});
    Plugins.StatusBar.setStyle({style: StatusBarStyle.Light});
  }
}
