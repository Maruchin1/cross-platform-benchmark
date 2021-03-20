import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-gallery-tem',
  templateUrl: './gallery-tem.component.html',
  styleUrls: ['./gallery-tem.component.scss'],
})
export class GalleryTemComponent implements OnInit {
  constructor() {
  }

  @Input() imageUrl: string;

  ngOnInit() {
  }

}
