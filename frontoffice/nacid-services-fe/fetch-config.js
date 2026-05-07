#!/usr/bin/node
const { ExternalConfigManager } = require("@duosoftbg/nacid-frontend-config-manager/dist/cjs/index");

const profile = process.argv[2];
console.log(`Active profile: ${profile}`);

let configServerUrl = "http://192.168.3.84:8301/nacid-config-server";
if (profile && (profile === "production" || profile === "stage")) {
  configServerUrl = "http://172.16.1.97:8301/nacid-config-server";
}

ExternalConfigManager.run({
  configServerUrl: configServerUrl,
  configName: "nacid-services-fe",
  profile: profile,
  allProfiles: ["dev", "office", "stage", "production"],
});
