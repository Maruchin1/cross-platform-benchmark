import {Component, OnInit} from '@angular/core';
import {LocalDatabaseService} from './core/local-database.service';
import {Plugins, StatusBarStyle} from '@capacitor/core';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(
    private localDatabaseService: LocalDatabaseService
  ) {
    Plugins.StatusBar.setBackgroundColor({color: '#ffffff'});
    Plugins.StatusBar.setStyle({style: StatusBarStyle.Light});
  }

  async ngOnInit() {
    await this.localDatabaseService.initDatabase();
  }
}
