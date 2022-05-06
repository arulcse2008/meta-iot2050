#
# Copyright (c) Siemens AG, 2021-2022
#
# Authors:
#  Quirin Gylstorff <quirin.gylstorff@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.  See
# COPYING.MIT file in the top-level directory.
#

# generate a swu image for a/b updating via swupdate
IMAGE_FSTYPES = "wic-swu-img"

require recipes-core/images/iot2050-image-example.bb

WKS_FILE = "iot2050-swu.wks.in"

# not compatible with SWUpdate images
IMAGE_INSTALL_remove = "regen-rootfs-uuid"
IMAGE_INSTALL_remove = "install-on-emmc"
IMAGE_INSTALL_remove = "node-red-preinstalled-nodes"

IMAGE_INSTALL += "swupdate"
IMAGE_INSTALL += "swupdate-handler-roundrobin"
IMAGE_INSTALL += "swupdate-complete-update-helper"
IMAGE_INSTALL += "iot2050-watchdog"

IMAGE_INSTALL += "data-partition"

FILESPATH_prepend := "${THISDIR}/files:"

ROOTFS_PARTITION_NAME = "${IMAGE_FULLNAME}.wic.img.p1.gz"

# Variables for swupdate image creation:
SRC_URI += "file://sw-description.tmpl"
TEMPLATE_FILES += "sw-description.tmpl"
TEMPLATE_VARS += "ROOTFS_PARTITION_NAME"
SWU_ADDITIONAL_FILES += "${ROOTFS_PARTITION_NAME}"
