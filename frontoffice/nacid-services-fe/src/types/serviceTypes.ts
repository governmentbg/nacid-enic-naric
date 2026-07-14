export interface ServiceConfig {
  baseHref: string;
  external: boolean;
  titleCode: string;
  descriptionCode?: string;
}

export interface ServicesDisplayPanelConfig {
  panel: "left" | "right";
  sections: ServicesDisplaySectionConfig[];
}

export interface ServicesDisplaySectionConfig {
  titleCode: string;
  services: ServiceConfig[];
}
